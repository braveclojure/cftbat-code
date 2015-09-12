class GlamourShotCaption
  attr_reader :text
  def initialize(text)
    @text = text
    clean!
  end

  private
  def clean!
    text.trim!
    text.gsub!(/lol/, "LOL")
  end
end

best = GlamourShotCaption.new("My boa constrictor is so sassy lol!  ")
best.text
; => "My boa constrictor is so sassy LOL!"
